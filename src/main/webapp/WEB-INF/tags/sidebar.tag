

<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header">Hi ${sessionScope['scopedTarget.sessionData'].user.name} !</li>
        <li><a href="/todo/todos"><i class="icon-home"></i> Home</a></li>
        <li><a href="/user/account"><i class="icon-user"></i> My account</a></li>
        <li><a href="/todo/new"><i class="icon-file"></i> Create a todo</a></li>
        <li class="divider"></li>
        <li class="nav-header">Search todo</li>
        <li>
            <form class="form-search" action="/todo/todos/search" id="searchForm" method="get">
                <div class="input-append">
                    <input type="text" name="title" class="input-small search-query" placeholder="search by title" required="required">
                    <button type="submit" class="btn">Go!</button>
                </div>
            </form>
        </li>
    </ul>
</div>