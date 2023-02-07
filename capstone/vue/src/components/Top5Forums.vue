<template>
  <div class = "top5">
      <h3>Top 5 Forums Today</h3>
        <nav>
        <router-link v-bind:to="{name: 'forum', params: {id: forum.id}}" v-for="forum in mostRecentForums" v-bind:key="forum.id">
            {{forum.forumName}}
        </router-link>
        </nav>
        
      
  </div>
</template>

<script>
import ForumsService from '../services/ForumsService';

export default {
    data() {
        return { mostRecentForums : []
        }
    
},

created() {
    ForumsService.displayTop5().then(
        (response) => {
            this.mostRecentForums = response.data;
        }
    );
    }
};
</script>

<style scoped>


.top5 {
  background-color: #f8f7f7;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);  
  height: 200%;
}

nav a {  
  display: flex;
  align-items: center;
  padding: 10px;
  width: 100%;
  text-align: left;
  text-decoration: none;
  color: black;
  
}

nav {
  display: flex;
  flex-direction: column;
  background-color: #f8f7f7;
  width: 100%;
  padding: 10px;
  
}
nav a:hover {
  background-color: #e0e0e0;
  width: 100%;
}
h3 {
  margin: 0;
  font-size: 20px;
  color: #23468A;
}

</style> 