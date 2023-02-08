<template>
  <div class = "top5">
      <h3>Top Forums Today</h3>
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


nav a {  
  display: flex;
  align-items: center;
  padding: 10px;
  width: 100%;
  text-align: left;
  text-decoration: none;
  color: black;
  
} 
 nav a:hover {
  background-color: #1489b417;
  width: 100%;
} 
h3 {
  margin: 0;
  font-size: 20px;
  color: #23468A;
}  

</style> 