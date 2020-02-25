package model;

class ItemFactory{
    public Item returnItemType(int type){
        if(type==1)
            return new RawItem();
        else if(type==2)
            return new ManufacturedItem();
        else if(type==3)
            return new ImportedItem();
        else
            return null;
    }
}