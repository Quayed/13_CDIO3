DROP TABLE IF EXISTS operator;

CREATE TABLE operator(opr_id INT PRIMARY KEY AUTO_INCREMENT, opr_name TEXT, ini TEXT, cpr TEXT, password TEXT) ENGINE=innoDB;
 
INSERT INTO operator(opr_id, opr_name, ini, cpr, password) VALUES
(1, 'Angelo A', 'AA', '070770-7007', 'lKje4fa'),
(2, 'Antonella B', 'AB', '080880-8008', 'atoJ21v'),
(3, 'Luigi C', 'LC', '090990-9009', 'jEfm5aQ');
