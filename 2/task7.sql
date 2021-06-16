select a.model,price from ( select l.model,l.price from laptop l union select p.model,p.price from printer p union select pc.model,pc.price from pc) a join product prod on a.model=prod.model where maker='Samsung'

