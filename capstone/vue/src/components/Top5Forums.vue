<template>
  <div class = "top5">
      <h3>Top 5 Forums Today</h3>
        <ul>
            <li v-for="forum in mostRecentForums" v-bind:key="forum.id">
                {{forum.forumName}}
            </li>
        </ul>
      
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
/* html {
    color: white;
    background-color: black;
} */

li{
    list-style: none;
}
</style> 