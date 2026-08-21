#include <stdio.h>
#include <string.h>

#define BUFFER_SIZE 100000

int main() {
  char buff[BUFFER_SIZE];
  FILE *f = fopen("input.txt", "r");
  fgets(buff, BUFFER_SIZE, f);

  int count = 0;

  for (int i = 0; i < strlen(buff); i++) {
    char curr = buff[i];
    if (curr == '(') {
      count++;
      // printf("%c: %d\n", curr, count);
    } else if (curr == ')') {
      count--;
      // printf("%c: %d\n", curr, count);
    }
  }

  printf("%d\n", count);

  fclose(f);
  return 0;
};
