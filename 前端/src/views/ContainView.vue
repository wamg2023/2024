<template>
  <div class="building">
    <div class="contain">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>

</script>

<style scoped>
.contain{
  height: 90%;
  width: 80%;
  margin-left: 10%;
  text-align: center;
  background-color: black;
  display: flex;
  margin-top: 2%;
  border-style: solid;
  border-width: 1px;
  border-color: white;
}

.building {
  background-color: black;
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}
</style>