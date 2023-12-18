<template>
  <NavBar />
  <router-view />
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default {
  name: 'App',
  components: {
    NavBar
  }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

nav img {
  height: 45px;
  margin-right: 80px;
}

nav {
  background-image: url('@/assets/navbg.png');
  width: auto;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
}

nav a {
  color: #9a9a9a;
  padding: 22px 60px;
  height: 80px;
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

nav .el-menu-item:hover {
  background-color: darkgray;
  border-bottom: solid 6px;
  border-bottom-color: brown;
  height: 80px;
}

nav a.router-link-exact-active {
  color: rgb(255, 196, 0);
  border-bottom: solid 6px;
  border-bottom-color: rgb(255, 196, 0);
  height: 80px;
}
</style>
