<template>
  <p>Create Forum</p>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    name: "create-forum",
    data() {
        return {
            forumName: {

            },
            forumDesc: {

            },
            forumRules: {

            }
        }
    },

    methods: {
        newTopic() {
            forumsService.createForum(this.forum).then(
                (response) => {
                    if(response.status === 201) {
                        //go to home page of new forum
                    }
                }
            )
        }
    }

}
</script>

<style>

</style>