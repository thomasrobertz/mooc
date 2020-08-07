create table #inventory 
([serial_number] INT PRIMARY KEY,
[price] REAL,
[name] varchar(20));

Insert into #inventory VALUES
 (2, 3.4, 'wrench'),
 (5, 1.4, 'bolt'),
 (4, 22.4, 'screw'),
 (1, 6.4, 'nail');
 
 select * from #inventory FOR XML AUTO, ROOT('items'), ELEMENTS  ; 
