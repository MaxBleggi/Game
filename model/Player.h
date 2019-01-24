/* header info */
#ifndef Player_h
#define Player_h

/* extern libraries */

/* user defined libraries */
#include "Cards.h"
#include "Inventory.h"

/* user defined data */


typedef struct PlayerKnight
{
    /* player attr */
    char* name;
    int health;
    int handSize;

    /* can create another struct for buffs */

    CardDeck* deck;
    Inventory* inventory;
} Player;

/* user defined functions */


#endif
