/* header info */
#ifndef Cards_h
#define Cards_h

/* extern libraries */

/* local libraries */

/* user defined data */
enum CardTypes
{
    Attack = 0;
    Skill = 1;
};

typedef struct Card
{
    enum CardTypes type;
    /* card attr below */
} Card;

typedef struct CardDeck
{
    int size;
    Card* nextCard;

} CardDeck;

/* user defined functions */


#endif
