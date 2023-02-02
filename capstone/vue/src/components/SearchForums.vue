<template>
  <div class="search-forum">
      <input type="text" placeholder="Search Forums" v-model="searchWord">
      <div v-if="searchWord" class="suggestions">
        <div v-for="forum in filteredList" v-bind:key="forum.id">
          <router-link v-bind:to="{name: 'forum', params: {id: forum.id}}"> {{ forum.forumName }}  </router-link>
        </div>
      </div>
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
.search-forum{
    display: flex;
    flex-direction: column;
}
.suggestions {
  background-color: rgb(224, 219, 219);
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.616);
  padding: 8px;
}
</style>
