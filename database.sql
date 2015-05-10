DROP TABLE IF EXISTS operator;

CREATE TABLE operator(
	opr_id INT PRIMARY KEY AUTO_INCREMENT,
	opr_name TEXT,
	ini TEXT,
	cpr TEXT,
	password TEXT
) ENGINE=innoDB;
 
INSERT INTO operator(opr_id, opr_name, ini, cpr, password) VALUES
(1, 'Angelo A', 'AA', '0707707007', 'lKje4fa'),
(2, 'Antonella B', 'AB', '0808808008', 'atoJ21v'),
(3, 'Luigi C', 'LC', '0909909009', 'jEfm5aQ');

ALTER TABLE tbl AUTO_INCREMENT = 11;