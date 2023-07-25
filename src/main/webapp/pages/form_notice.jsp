<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create notice</title>
    <link rel="stylesheet" type="text/css" href="css/form_notice.css">
</head>
<body>
<div class="container">
    <h1>Заполните заявку</h1>
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="command" value="create_notice">
        <label for="title">Заголовок:</label>
        <input type="text" id="title" name="title" maxlength="45" placeholder="Введите заголовок" required>
        ${errorTitleMessage}<br>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" placeholder="Введите имя" required>
        ${errorNamePersonMessage}<br>
        <label for="surname">Фамилия:</label>
        <input type="text" id="surname" name="surname" placeholder="Введите фамилию" required>
        ${errorSurnamePersonMessage}<br>
        <label for="age">Возраст:</label>
        <input type="text" id="age" name="age" maxlength="13" placeholder="Введите возраст" required>
        ${errorAgeMessage}<br>
        <label for="description">Описание:</label>
        <textarea id="description" name="description" placeholder="Введите описание" required></textarea>
        ${errorDescriptionMessage}<br>
        <label for="execution_time">Время выполнения:</label>
        <input type="text" id="execution_time" name="execution_time" placeholder="Введите время выполнения заявки" required>
        ${errorExecutionTimeMessage}<br>
        <label for="reward">Вознаграждение:</label>
        <input type="text" id="reward" name="reward" placeholder="Введите вознаграждение" required>
        ${errorRewardMessage}<br>
        <label for="status">Статус:</label>
        <select id="status" name="person_status" required>
            <option value="missing">missing</option>
            <option value="criminal">criminal</option>
        </select><br>
        <input type="submit" value="Подать заявку"><br>
    </form>
</div>
</body>
</html>
