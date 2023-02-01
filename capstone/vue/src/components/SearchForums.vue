<template>
  <div class="search-forums">
      <input type="text" placeholder="Search Forums" v-model="searchWord">
      <router-link v-bind:to="{name: 'forum', params: {id: forum.id}}" v-for="forum in filteredList" v-bind:key="forum.id">
         <div> {{ forum.forumName }} </div>
          <div>{{forum.description}} </div>
        </router-link>
  </div>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    data(){
        return {
            searchWord: "",
            allForums: []
        }
    },
    computed : {
        filteredList(){
            return this.allForums.filter((forum)=>{
                if(forum.forumName.toLowerCase().includes(this.searchWord.toLowerCase())){
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
.search-forums{
    display: flex;
    flex-direction: column;
}

</style>