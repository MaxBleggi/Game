/* header info */
#ifndef Inventory_h
#define Inventory_h

/* extern libraries */

/* user defined libraries */

/* user defined data */
enum HeadWearableItems
{
    naked = 0;
};

enum TorsoWearableItems
{
    naked = 0;
};

enum LegWearableItems
{
    naked = 0;
};

enum FootWearableItems
{
    naked = 0;
};

enum HandWearableItems
{
    naked = 0;
};

enum HandActionItems
{
    naked = 0;
};

enum InventoryItemTypes
{
    HeadWearable = 0;
    TorsoWearable = 1;
    LegWearable = 2;
    FootWearable = 3;
    HandWearable = 4;
    Misc = 5;
};

typedef struct InventoryItem
{
    /* item attr */
} InventoryItem;

typedef struct Inventory
{
    int inventorySize;
    enum HeadWearableItems head;
    enum TorsoWearableItems torso;
    enum LegWearableItems legs;
    enum FootWearableItems feet;
    enum HandWearableItems hands;
    enum HandActionItems leftHand;
    enum HandActionItems rightHand;

    InventoryItem* nextItem;
} Inventory;


/* user defined functions */

#endif
