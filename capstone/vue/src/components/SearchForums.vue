<template>
  <div class="search-forums">
      <input type="text" placeholder="Search Forums" v-model="searchWord">
      <p v-for="forum in filteredList" v-bind:key="forum.id">{{ forum.forumName }}</p>
  </div>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    data(){
        return {
            searchWord: '',
            allForums: []
        }
    },
    computed : {
        filteredList(){
            return this.allForums.filter((forum)=>{
                if(forum.forumName.toLowerCase().includes(this.searchWord.toLowerCase()) || this.searchWord == ''){
                    return forum;
                }
            })
                
        }
    },
    created(){
        forumsService.getAllForums().then((response) => {
            this.allForums = response.data;
        });
    }

}
</script>

<style>

</style>