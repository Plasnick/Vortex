<template> 
  <div id="app">
    <div id="nav">

      <header>

          <router-link v-bind:to="{ name: 'home' }">
          <img src="/vortex.png" alt="logo" class="logo"/>
        </router-link>

      <div class="search-container">
        <search-bar />
      </div>

       <div class="header-btns">
        <router-link v-bind:to="{ name: 'home' }"></router-link>&nbsp;&nbsp;

        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"><button class="btn header-button" id="login-button">Log Out</button></router-link>

        <router-link v-bind:to="{ name: 'login'}" v-else>
          <button class="btn header-button" id="login-button">Log In</button>
        </router-link>&nbsp;&nbsp;&nbsp;&nbsp;
        <router-link v-bind:to="{ name: 'account'}" v-show="$store.state.token !=''">
          <i class="fa-solid fa-user"></i>
        </router-link>
        <router-link v-bind:to="{ name: 'forumSearch'}"></router-link>
      </div>

      </header>

    </div>
    <router-view />
  </div>
</template>

<script>
import SearchBar from "./components/SearchBar.vue";

export default{
  components: {
    SearchBar,
  }
}
</script>
<style scoped >
header {
  width: 100%;
  height: 80px;
  border: none;
  border-bottom: 1px solid #d8d8d8;
  display: flex;
  justify-content: space-between;
  align-items: center;
  grid-area: header;
  position: fixed;
  top: 0;
  z-index: 1;
  background-color: #fcfcfc;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
  
  
}
 .search-container{
  border: 1px solid #d8d8d8;
  border-radius: 25px;
  padding: 8px 20px;
  width: 35%;
  height: 25px;
  align-items: center;
  justify-content: center;
  background-color: #ececec;
  display: flex; 
  justify-content: center;
  flex-direction: column;
  outline-style: none;
    
}

.logo {
  height: 100%;
  margin-left: 15px;
}

.links-container {
  display: flex;
  justify-content: flex-end;
  font-size: 0;
}

.links-container a {
  font-size: 16px;
  margin: 0 10px;
  text-decoration: none;
  color: #23468A;
}
.header-btns{
  margin-right: 40px;
}

.btn{
  border-radius: 13px;
  width: 115px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer;
}

#login-button{
  background-color: #23468A;
  color: #f3f3f3;
}

#login-button:hover {
  background-color: #1483d6;
}
i{
  color: #23468A;
}
</style>

<style>
body{
  font-family: 'Cabin';
}
</style>



