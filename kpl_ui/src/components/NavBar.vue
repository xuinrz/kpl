<template>
  <nav>
    <img src="@/assets/logo3.webp">
    <router-link to="/">赛程数据</router-link>
    <router-link to="/team">战队数据</router-link>
    <router-link to="/player">选手数据</router-link>
    <router-link to="/hero">英雄数据</router-link>
  </nav>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
nav img {
  height: 45px;
  margin-right: 80px;
}

nav {
  background-image: url('@/assets/navbg.png');
  background-repeat: repeat;
  background-size:100%;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 5px 10px 1px rgba(0, 0, 0, 0.5);
}

nav a {
  color: #d5d5d5;
  padding: 29px 60px;
  height: 80px;
  min-width: 210px;
  position: relative;
  text-align: center;
  border-bottom: transparent;
  display: flex;
  transition: 0.5s;
  font-size: 22px;
  text-decoration-line: none;
  font-weight: bold;
}

nav a:hover {
  background-color: rgba(0, 0, 0, 0.293);
  color: rgb(255, 196, 0);
  border-bottom: solid 6px;
  border-bottom-color: rgb(255, 196, 0);
  height: 80px;
}

nav a.router-link-exact-active {
  color: rgb(255, 196, 0);
  border-bottom: solid 6px;
  border-bottom-color: rgb(255, 196, 0);
  height: 80px;
}
</style>
