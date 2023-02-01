<template>
  


   <form v-on:submit.prevent="submitForm" class="cardForm"> 
    <p>Create Forum</p>

    <label for="name">Forum Name</label>
    <input
    id="name"
    type="text"
    class="form-control"
    v-model="forum.title"
    autocomplete="off"
    />

    <label for="description"> Forum Description</label>
    <textarea
        id="description"
        class="form-control"
        v-model="forum.description"
    />

    <label for="rules"> Desscribe Your Rules Here</label>
    <textarea
        id="rules"
        class="form-control"
        v-model="forum.rules"
    />    

    <button>Submit</button>
    <button v-on:click.prevent="cancelForm"
      type="cancel"> Cancel 
    </button>  

   </form>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    // name: "create-forum",
    data() {
        return {
            forum: {
                name: '',
                description: '',
                rules: '',
                moderators: ''
            }
        }
    },

    methods: {
        submitForum() {

          const newForum = {
              name: this.forum.name,
              description: this.forum.description,
              rules: this.forum.rules,
              moderators: this.$state.user.id
            
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