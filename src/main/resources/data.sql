INSERT INTO `mini_project_be`.`users` (`id`, `email`, `name`, `password`)
VALUES
('1', 'a@a', 'a', 'a'),
('2', 'b@b', 'b', 'b');

INSERT INTO `mini_project_be`.`events` (`id`, `name`, `start_date`, `end_date`, `thumbnail`, `detail`)
VALUES
('1', '햇반컵반 BIG 구매하고 한정판 굿즈 받기', '2023-01-16', '2023-02-28', 'https://www.emart24.co.kr/image/NDg1OA==', 'https://emart24.co.kr/image/NTAwNA=='),
('2', '밀키트 예약픽업 10% 할인+페이백', '2023-02-01', '2023-02-28', 'https://www.emart24.co.kr/image/NDkzOA==', 'https://emart24.co.kr/image/NDg2NQ==');

INSERT INTO `mini_project_be`.`products` (`id`, `name`, `category`, `description`, `price`, `image`)
VALUES
('1', '롯데 빈츠 2입 리미티드기획 408g', '크래커', '초콜릿가공품, 대륙식품(주) 덕계공장 경남 양산시 그린공단3길 108 (한국), 원산지: 상세설명참조', '5370', 'https://sitem.ssgcdn.com/59/56/18/item/1000530185659_i1_1100.jpg'),
('2', '킷캣 오리지널 18입', '초콜릿', '킷캣 오리지널 18입, 네슬레코리아 (제조국 : 아랍에미리트)', '9980', 'https://sitem.ssgcdn.com/21/16/61/item/1000529611621_i1_1100.jpg'),
('3', '허쉬 다크초콜릿 아사이&블루베리210g', '초콜릿', '허쉬 다크초콜릿 아사이&블루베리210g, 초콜릿가공품', '9080', 'https://sitem.ssgcdn.com/90/11/54/item/1000270541190_i1_1100.jpg'),
('4', '[린트]엑설런스 다크 99% 50g', '초콜릿', 'LINDT&SPRUNGLI SAS / (주)농심 / 서울특별시 동작구 여의대방로112(신대방동) (제조국 : 상세설명참조)', '6860', 'https://sitem.ssgcdn.com/50/75/43/item/1000029437550_i1_1100.jpg');

INSERT INTO `mini_project_be`.`carts` (`id`, `product_id`, `user_id`)
VALUES
('1', '2', '1'),
('2', '1', '1'),
('3', '3', '2');

