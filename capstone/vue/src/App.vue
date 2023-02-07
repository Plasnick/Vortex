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
      <div class="links-container">
        <router-link v-bind:to="{ name: 'home' }"></router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{ name: 'login'}" v-else>Login</router-link>&nbsp;|&nbsp;
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
  width: 100%;
  border: none;
  border-bottom: 1px solid #D4D4D4;
  /* padding: 20px; */
  display: flex;
  justify-content: space-between;
  align-items: center;
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

</style>