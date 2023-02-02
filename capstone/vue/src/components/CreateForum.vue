<template>
  


   <form v-on:submit.prevent="submitForum" class="cardForm"> 
    <p>Create Forum</p>


<div>
    <label for="name">Forum Name</label>
    <input
    id="name"
    type="text"
    class="form-control"
    v-model="forum.forumName"
    autocomplete="off"
    />
</div>    

<div>
    <label for="description"> Forum Description</label>
    <textarea
        id="description"
        class="form-control"
        v-model="forum.description"
    />
</div>    

<div>
    <label for="rules"> Desscribe Your Rules Here</label>
    <textarea
        id="rules"
        class="form-control"
        v-model="forum.rules"
    /> 
</div>       

<div>
    <input type="submit"/>
    <button v-on:click.prevent="cancelForm"
      type="cancel"> Cancel 
    </button>  
</div>    

   </form>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    // name: "create-forum",
    data() {
        return {
            forum: {
                forumName: '',
                description: '',
                rules: '',
                moderatorList: []
            }
        }
    },

    methods: {
        submitForum() {

          const newForum = {
              forumName: this.forum.forumName,
              description: this.forum.description,
              rules: this.forum.rules,
              moderatorList: [this.$store.state.user.id]
            
          };

            forumsService.createForum(newForum).then(
                (response) => {
                    if(response.status === 201) {
                        this.$router.push({name: "forum", params: {id: response.data.id}})
                    }
                }
            )
        }
    }
}
</script>

<style>

</style>