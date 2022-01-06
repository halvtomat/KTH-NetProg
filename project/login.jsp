<html>
	<style>
		header {
			display: flex;
			flex-direction: row;
			background-color: #456990;
			justify-content: space-between;
			width: 100%;
		}
		header * {
			top: 50%;
			display: block;
			text-decoration: none;
			margin: 10px;
		}
		header div {
			display: flex;
			flex-direction: row;
		}
		.mainContainer {
			display: flex;
			flex-direction: column;
			width: 100%;
			height: 100%;
		}
		.headerTitle {
			color: white;
		}
		.headerLink {
			color: rgba(255,255,255,.5);
			font-size: 1.2rem;
		}
		.username {
			color: white;
			font-size: 1.2rem;
		}
		.formContainer {
			margin: 0 auto;
			padding: 40px;
			border: 1px solid black;
			border-radius: 4px;
			margin-top: 100px;
		}
		form {
			display: flex;
			flex-direction: column;
		}
		form * {
			margin: 5px;
			font-size: 1.2rem;
		}
	</style>
	<head>
		<title>Stay A While Longer</title>
	</head>
	<body>
		<div class="mainContainer">
			<header>
				<div>
					<h2 class="headerTitle">stay a while longer</h2>
					<a class="headerLink" href="/project/menu">Home</a>
				</div>
				<div>
					<p class=username>-</p>
					<a class="headerLink" href="/project/logout">Logout</a>
				</div>
			</header>
			<div class="formContainer">
				<form action="login" method="post">
					<input type="text" name="username" placeholder="Username">
					<input type="password" name="password" placeholder="Password">
					<input type="submit" value="login" name="action">
					<input type="submit" value="register" name="action">
				</form>
			</div>
		</div>
	</body>
</html>
