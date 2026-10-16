use std::collections::HashMap;

enum ValueType {
    List(Vec<ValueType>),
    IntVal(i32),
    StringVal(String),
    JsonObj(HashMap<String, ValueType>),
}

fn get_json_sum(m: &HashMap<String, ValueType>) -> i32 {
    let contains_red = m.values().any(|it| {
        if let ValueType::StringVal(it) = it {
            if it.as_str() == "red" { true } else { false }
        } else {
            false
        }
    });

    if contains_red {
        return 0;
    }

    return m
        .values()
        .map(|it| match it {
            ValueType::IntVal(i) => i.clone(),
            ValueType::List(i) => get_list_sum(&i),
            ValueType::JsonObj(i) => get_json_sum(i),
            _ => 0,
        })
        .sum();
}

fn get_list_sum(list: &Vec<ValueType>) -> i32 {
    list.iter()
        .map(|it| match it {
            ValueType::IntVal(i) => i.clone(),
            ValueType::List(i) => get_list_sum(&i),
            ValueType::JsonObj(i) => get_json_sum(i),
            _ => 0,
        })
        .sum()
}

fn print_list(list: &Vec<ValueType>) {
    list.iter().for_each(|it| match it {
        ValueType::IntVal(i) => println!("{}", i),
        ValueType::List(i) => print_list(&i),
        ValueType::JsonObj(_) => println!("jblob"),
        _ => println!("doing nish"),
    });
}

fn parse_json(start_index: usize, bytes: &[u8]) -> (usize, HashMap<String, ValueType>) {
    let mut i = start_index;
    let mut items = HashMap::new();
    let mut key: Option<String> = None;

    loop {
        let curr = bytes[i];
        println!("{}", curr as char);
        match curr {
            b'[' => {
                let (next_index, list) = parse_list(i + 1, bytes);
                let k = key.unwrap();
                items.insert(k, ValueType::List(list));
                i = next_index + 1;
                key = None;
            }
            b'{' => {
                let (next_index, json_blob) = parse_json(i + 1, bytes);
                let k = key.unwrap();
                items.insert(k, ValueType::JsonObj(json_blob));
                i = next_index + 1;
                key = None;
            }
            b'"' => {
                i += 1;
                let l = i;
                let mut r = 0;

                while bytes[i] != b'"' {
                    i += 1;
                    r += 1;
                }

                r = r + l;
                i += 1;

                let bs = bytes[l..r].to_vec();
                let new_key = String::from_utf8(bs).unwrap();
                println!("Parsed str {}", new_key);
                if key.is_none() {
                    key = Some(new_key);
                } else {
                    let k = key.unwrap();
                    items.insert(k, ValueType::StringVal(new_key));
                    key = None;
                }
            }
            b',' | b' ' | b':' => {
                i += 1;
            }
            b'}' | b'\n' => return (i, items),
            _ => {
                if !curr.is_ascii_digit() && curr != b'-' {
                    panic!("wtf");
                }

                let l = i;
                let mut r = 0;

                i += 1;
                while bytes[i].is_ascii_digit() || bytes[i] == b'-' {
                    i += 1;
                    r += 1;
                }

                r = r + l + 1;

                let bs = bytes[l..r].to_vec();
                let num = String::from_utf8(bs).unwrap().parse::<i32>().unwrap();
                let k = key.unwrap();
                items.insert(k, ValueType::IntVal(num));
                key = None;
            }
        }
    }
}

fn parse_list(start_index: usize, bytes: &[u8]) -> (usize, Vec<ValueType>) {
    let mut i = start_index;

    let mut items: Vec<ValueType> = vec![];

    loop {
        let curr = bytes[i];
        //println!("parsing {} at {i}", curr as char);
        match curr {
            b'[' => {
                let (next_index, list) = parse_list(i + 1, bytes);
                items.push(ValueType::List(list));
                i = next_index + 1;
            }
            b'{' => {
                println!("Parsing json at {i}");
                let (next_index, json_blob) = parse_json(i + 1, bytes);
                items.push(ValueType::JsonObj(json_blob));
                i = next_index + 1;
            }
            b'"' => {
                i += 1;
                while bytes[i] != b'"' {
                    i += 1;
                }
                i += 1;
            }
            b',' | b' ' => {
                i += 1;
            }
            b']' | b'\n' => return (i, items),
            _ => {
                if !curr.is_ascii_digit() && curr != b'-' {
                    panic!("wtf");
                }

                let l = i;
                let mut r = 0;

                i += 1;
                while bytes[i].is_ascii_digit() || bytes[i] == b'-' {
                    i += 1;
                    r += 1;
                }

                r = r + l + 1;

                let bs = bytes[l..r].to_vec();
                let num = String::from_utf8(bs).unwrap().parse::<i32>().unwrap();
                items.push(ValueType::IntVal(num));
            }
        }
    }
}

fn main() {
    let input = include_str!("./input.txt");
    println!("{}", &input[70..]);
    let res = parse_list(0, input.as_bytes()).1;

    let abc = &res[0];

    if let ValueType::List(abc) = abc {
        let sum = get_list_sum(&abc);
        println!("Sum is {sum}");
    }
}
