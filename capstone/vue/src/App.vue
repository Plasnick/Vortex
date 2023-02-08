<template> 
  <div id="app">
    <div id="nav">
      <header>
          <router-link v-bind:to="{ name: 'home' }">
          <img src="/vortex.png" alt="logo" class="logo"/>
        </router-link>
      <div class="search-forum-container">
        <search-forums />
      </div>
      <div class="header-btns">
        <router-link v-bind:to="{ name: 'home' }"></router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
          <button class="btn header-button" id="logout-button">Logout</button>
          </router-link>
        <router-link v-bind:to="{ name: 'login'}" v-else>
          <button class="btn header-button" id="login-button">Login</button>
        </router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'forumSearch'}"></router-link>
      </div>
      </header>
    </div>
    <router-view />
  </div>
</template>
<script>
import SearchForums from './components/SearchForums.vue'
export default{
  components: {
    SearchForums,
  }
}
</script>
<style scoped>


header {
  position: sticky;
  top: 0;
  width: 100%;
  border-bottom: 1px solid #D4D4D4;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0px 3px 7px -8px rgba(0,0,0,0.31);

}
 
.search-forum-container{
  width: 60%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo {
  max-width: 100px;
  height: auto;
  margin-right: 20px;
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
.header-button{
  margin-right: 0.5rem;
}

.btn{
  border-radius: 13px;
  width: 105px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer;
}

#login-button{
  background-color: #23468A;
  color: #eee;
}

#login-button:hover {
  background-color: #1483d6;
}
</style>

<style>
body{
  font-family: 'Cabin';
}
</style>