fn main() {
    let input = "1113122113";

    let mut bytes = input.as_bytes().to_vec();
    for _ in 0..40 {
        let mut vals: Vec<(usize, u8)> = Vec::new();
        let mut i = 0;
        while i < bytes.len() {
            let val = bytes[i];
            let mut j = i;
            while j < bytes.len() && bytes[j] == val {
                j += 1;
            }
            // println!("Found {} {}'s", j - i, val as char);
            vals.push((j - i, val));
            i += j - i;
        }

        //dbg!(&vals);
        let mut new_bytes: Vec<u8> = Vec::new();
        vals.iter().for_each(|(count, val)| {
            let count = char::from_digit(count.clone() as u32, 10).unwrap() as u8;
            new_bytes.push(count);
            new_bytes.push(val.clone());
        });
        // dbg!(&new_bytes);
        bytes = new_bytes;
        //dbg!(&bytes);
    }
    // match String::from_utf8(bytes.clone()) {
    //     Ok(string) => println!("Success: {}", string),
    //     Err(e) => println!("Invalid UTF-8 sequence: {}", e),
    // }
    dbg!(bytes.len());

    //}
}
