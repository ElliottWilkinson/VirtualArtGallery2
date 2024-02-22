<template>
  <section id="art-details">
      <article class="art-details">
          <div class="art-image">
              <img :src="artPiece.imageName">
          </div>
          <div class="art-caption">
              {{artPiece.caption}}
          </div>
          <div class="art-description">
              {{artPieced.description}}
          </div>
      </article>
  </section>
</template>

<script>
export default {

    props: ['artPiece'], 

    computed: { 
        isLoggedIn(){
            return this.$store.token.length > 0;
        }
    }
}
</script>

<style>

</style>