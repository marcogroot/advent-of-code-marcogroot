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
    long a = (l * w);
    long b = (w * h);
    long c = (h * l);
    long min;
    if (a <= b && a <= c) {
      min = a;
    } else if (b <= a && b <= c) {
      min = b;
    } else {
      min = c;
    }
    long val = (2 * a) + (2 * b) + (2 * c) + min;
    printf("%ld %ld %ld = %ld\n", a, b, c, val);
    sum += val;
  }
  printf("%ld\n", sum);

  fclose(file);
  return 0;
}
