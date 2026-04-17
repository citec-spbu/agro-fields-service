-- liquibase formatted sql

-- changeset cursor:addCultivarsDictionary

CREATE TABLE IF NOT EXISTS cultivar
(
  cultivar_id   BIGSERIAL PRIMARY KEY,
  crop_id       BIGINT      NOT NULL,
  cultivar_name VARCHAR(80) NOT NULL,
  CONSTRAINT fk_cultivar_crop
      FOREIGN KEY (crop_id) REFERENCES crop (crop_id),
  CONSTRAINT uq_cultivar_crop_name
      UNIQUE (crop_id, cultivar_name)
);

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Каберне Совиньон' FROM crop WHERE crop_name = 'Виноград'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Мерло' FROM crop WHERE crop_name = 'Виноград'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Шардоне' FROM crop WHERE crop_name = 'Виноград'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Рислинг' FROM crop WHERE crop_name = 'Виноград'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Изабелла' FROM crop WHERE crop_name = 'Виноград'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Дарья' FROM crop WHERE crop_name = 'Пшеница красная яровая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Ирень' FROM crop WHERE crop_name = 'Пшеница красная яровая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Лада' FROM crop WHERE crop_name = 'Пшеница красная яровая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Омская 36' FROM crop WHERE crop_name = 'Пшеница красная яровая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Московская 39' FROM crop WHERE crop_name = 'Пшеница красная озимая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Безостая 100' FROM crop WHERE crop_name = 'Пшеница красная озимая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Немчиновская 57' FROM crop WHERE crop_name = 'Пшеница красная озимая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Безенчукская 182' FROM crop WHERE crop_name = 'Пшеница твердая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Никола' FROM crop WHERE crop_name = 'Пшеница твердая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Руно' FROM crop WHERE crop_name = 'Пшеница спельта'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Швабенкорн' FROM crop WHERE crop_name = 'Пшеница спельта'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Вакула' FROM crop WHERE crop_name = 'Ячмень яровой'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Гонар' FROM crop WHERE crop_name = 'Ячмень яровой'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Добрыня 3' FROM crop WHERE crop_name = 'Ячмень озимый'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Самсон' FROM crop WHERE crop_name = 'Ячмень озимый'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Краснодарский 291 АМВ' FROM crop WHERE crop_name = 'Кукуруза на зерно'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Ладожский 191' FROM crop WHERE crop_name = 'Кукуруза на зерно'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Росс 140 СВ' FROM crop WHERE crop_name = 'Кукуруза на силос'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Краснодарский 194 МВ' FROM crop WHERE crop_name = 'Кукуруза на силос'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Лакомка' FROM crop WHERE crop_name = 'Подсолнечник'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Бузулук' FROM crop WHERE crop_name = 'Подсолнечник'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Липецкий' FROM crop WHERE crop_name = 'Рапс яровой'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Атлант' FROM crop WHERE crop_name = 'Рапс яровой'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Северянин' FROM crop WHERE crop_name = 'Рапс озимый'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Лорис' FROM crop WHERE crop_name = 'Рапс озимый'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Аннушка' FROM crop WHERE crop_name = 'Соя'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Лира' FROM crop WHERE crop_name = 'Соя'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Мадонна' FROM crop WHERE crop_name = 'Горох'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Фараон' FROM crop WHERE crop_name = 'Горох'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Гала' FROM crop WHERE crop_name = 'Картофель'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Невский' FROM crop WHERE crop_name = 'Картофель'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'РМС 127' FROM crop WHERE crop_name = 'Свёкла сахарная'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Крокодил' FROM crop WHERE crop_name = 'Свёкла сахарная'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Бордо 237' FROM crop WHERE crop_name = 'Свёкла столовая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Мулатка' FROM crop WHERE crop_name = 'Свёкла столовая'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Нантская 4' FROM crop WHERE crop_name = 'Морковь'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Самсон' FROM crop WHERE crop_name = 'Морковь'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Слава 1305' FROM crop WHERE crop_name = 'Капуста кочанная'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Агрессор F1' FROM crop WHERE crop_name = 'Капуста кочанная'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Герман F1' FROM crop WHERE crop_name = 'Огурцы'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Кураж F1' FROM crop WHERE crop_name = 'Огурцы'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Бычье сердце' FROM crop WHERE crop_name = 'Помидоры'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Де Барао' FROM crop WHERE crop_name = 'Помидоры'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Штутгартер Ризен' FROM crop WHERE crop_name = 'Лук'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Ред Барон' FROM crop WHERE crop_name = 'Лук'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Любаша' FROM crop WHERE crop_name = 'Чеснок'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Комсомолец' FROM crop WHERE crop_name = 'Чеснок'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Россиянка' FROM crop WHERE crop_name = 'Тыква'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Конфетка' FROM crop WHERE crop_name = 'Тыква'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Грибовские 37' FROM crop WHERE crop_name = 'Кабачки'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Искандер F1' FROM crop WHERE crop_name = 'Кабачки'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Антоновка' FROM crop WHERE crop_name = 'Яблоки'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Гала' FROM crop WHERE crop_name = 'Яблоки'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Лада' FROM crop WHERE crop_name = 'Груши'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Конференция' FROM crop WHERE crop_name = 'Груши'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Стенлей' FROM crop WHERE crop_name = 'Слива'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Венгерка' FROM crop WHERE crop_name = 'Слива'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Шоколадница' FROM crop WHERE crop_name = 'Вишня'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Тургеневка' FROM crop WHERE crop_name = 'Вишня'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Ипуть' FROM crop WHERE crop_name = 'Черешня'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Фатеж' FROM crop WHERE crop_name = 'Черешня'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Азия' FROM crop WHERE crop_name = 'Клубника'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Альба' FROM crop WHERE crop_name = 'Клубника'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Гусар' FROM crop WHERE crop_name = 'Малина'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Полька' FROM crop WHERE crop_name = 'Малина'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;

INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Селеченская 2' FROM crop WHERE crop_name = 'Смородина'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
INSERT INTO cultivar (crop_id, cultivar_name)
SELECT crop_id, 'Ядрёная' FROM crop WHERE crop_name = 'Смородина'
ON CONFLICT (crop_id, cultivar_name) DO NOTHING;
