#include <stdio.h>
#include <string.h>

#define MAX_LINE 1024

int main(void) {
  FILE *file = fopen("input.txt", "r");
  if (!file)
    return 1;

  char line[MAX_LINE];
  long sum = 0;
  while (fgets(line, sizeof(line), file)) {
    // Strip the trailing newline character safely
    line[strcspn(line, "\r\n")] = '\0';

    if (strlen(line) == 0) {
      continue;
    }

    long l, w, h;
    sscanf(line, "%ldx%ldx%ld", &l, &w, &h);
    long a = (l * 2);
    long b = (w * 2);
    long c = a + b;
    long val = c + (l * w * h);
    printf("%ld %ld %ld = %ld\n", a, b, c, val);
    sum += val;
  }
  printf("%ld\n", sum);

  fclose(file);
  return 0;
}
