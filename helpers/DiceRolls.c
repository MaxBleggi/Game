#include <stdlib.h>

/**
 * Generates a psuedo-random number between 0-numberOfSides
 * simulating a dice roll
 * returns: int with range (1-numberOfSides)
 */
int xSidedDiceRoll(int numberOfSides)
{
  time_t t;
  int i;
  int rnd;

  /* Intializes random number generator */
  rnd =  rand() % numberOfSides;

  /* return rnd number from 1-20 */
  return rnd + 1;
}
