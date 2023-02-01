<template>
  <p>Create Forum</p>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    name: "create-forum",
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
              moderators: this.$.state.user.id
            
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