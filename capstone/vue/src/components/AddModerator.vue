<template>
<div class="add-moderator">
  <label for="user-select">Select a user:</label>
  <select id="user-select" v-model="selectedUserId">
      <option v-for="user in users" v-bind:key="user.id" v-bind:value="user.id">
          {{user.username}}
      </option>
  </select>
  <button v-on:click="addModerator" >Add Moderator</button>
  <p id = "message" v-show="displayMessage">{{message}}</p>
  </div>
</template>

<script>
import usersService from "../services/UsersService"
import moderatorsService from "../services/ModeratorsService"

export default {
    data(){
        return{
            users: [],
            selectedUserId: null,
            message: '',
            displayMessage: false
        }
    },
    methods: {
        addModerator(){
            let isModerator = false;
            for(let i =0; i < this.$store.state.moderatorsForForum.length; i++){
                let currentObj = this.$store.state.moderatorsForForum[i]
                if(currentObj.moderatorId === this.selectedUserId){
                    isModerator = true;
                }
            }
            if(isModerator){
                this.message = 'User is already a moderator.'
                this.displayMessage = true;
            }
            else{
                let newModerator = {
                    moderatorId : this.selectedUserId,
                    forumId : this.$route.params.id
                }
                moderatorsService.addModerator(newModerator).then((response)=>{
                    if(response.status==201){
                       let index = this.users.findIndex((element)=>element.id ==this.selectedUserId)
                        let newModForForum = {
                            moderatorId : this.selectedUserId,
                            forumId : this.$route.params.id,
                            username : this.users[index].username
                        }
                        this.$store.commit("ADD_MODERATOR", newModForForum)
                        this.message = "User added as moderator"
                        this.displayMessage = true;
                    }
                })
            }
        }
    },
    created(){
        usersService.getAllUsers().then((response) =>{
            this.users = response.data
        })
    }

}
</script>

<style>

</style>