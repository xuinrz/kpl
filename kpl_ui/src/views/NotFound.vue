<template>
  <div class="notfound">
      <img src="@/assets/404.gif"/>
      <p>您访问的页面不存在</p>
      <router-link to="/">
        <button>返 回 首 页</button>
      </router-link>
  </div>
</template>

<style scoped>
.notfound img {
  margin: 0px;
  width: 500px;
  position: relative;
}
.notfound p{
  font-size: 30px;
}
.notfound button{
  margin: 20px;
  width: 160px;
  height: 50px;
  font-size: 20px;
  border-radius: 10px;
  border:none
}


</style>
