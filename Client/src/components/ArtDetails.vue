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

    #art-details{
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        padding: 10px;
    }

    .art-details{
        display: grid;
        grid-template-areas:
        "image image"
        "title title"
        "description description";
        color: white;
        background-color: black;
        padding: 10px;

    }

    .art-image{
        grid-area: image;
    }

    .art-caption{
        grid-area: title;
    }

    .art-description {
        grid-area: description;
    }

</style>