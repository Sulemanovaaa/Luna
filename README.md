# Бот-повар

Изначально пользователь может заказать какое то блюдо. Бот получает задание, начинает поэтапно его выполнять. Возьмём шаурму, разобьем по этапно:
1) Порезать овощи
2) Покрутить гриль
3) Порезать курочку
4) Пожарить лаваш
5) Добавить соус
6) Завернуть лаваш
7) Отдать клиенту
На каждый этап отводиться определенное время, а в эти промежутки(по 10-15 сек), участники могут делать всякие пакости или наоборот помогать. Получается многопользовательская игра, где каждый из героев через телеграмм сможет как то повлиять на качество конечного продукта. Например во время резки овощей можно подкинуть испорченный помидор, тогда бот расстроиться и на этом этапе он с условной вероятностью в 70% порежет овощи слишком крупно. Другие действия: подкинуть таракана, поторопить клиентов повара, похвалить повара.

**То есть, эмоции повара влияют на конечный продукт.**
Проект реализуется на языке Java, бот добавляется в Telegramm

**Ccылка на бота: https://t.me/Emotional_cookbot**
