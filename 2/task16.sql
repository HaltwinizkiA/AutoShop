select  table1.model,table2.model , table1.speed, table1.ram from PC table1 join PC table2 where table1.speed=table2.speed and table1.ram=table2.ram and table1.code<table2.code
