CREATE TABLE customer (
  id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  tier varchar(50) NOT NULL
);

CREATE TABLE customer_point (
  id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  order_number varchar(255) NOT NULL UNIQUE,
  customer_id bigint NOT NULL REFERENCES customer,
  points int NOT NULL,
  date DATE NOT NULL DEFAULT CURRENT_DATE
);