<template>
  <div class="layout">
    <div class="schedulelist">
      <a-list item-layout="horizontal" :data-source="results" :scroll="{ y: 'calc(80vh - 20px)' }">
        <template #renderItem="{ item, index }">
          <a-list-item @click="selectMatch(index)" :class="{ win: item.select, smatch: index == sm }">
            <a-list-item-meta>
              <template #title>
                <span class="date" v-if="index == 0 || notSameDay(index)">{{ formatDate(item.start_time) }}</span>
                <br v-if="index == 0 || notSameDay(index)" />
                <br v-if="index == 0 || notSameDay(index)" />
                <img :src=item.camp1.team_icon>
                <span class="tname">{{ item.camp1.team_name }}</span>
                <span class="score" :class="{ win: item.camp1.is_win }">{{ item.camp1.score }}</span>
                <br /><br />
                <img :src=item.camp2.team_icon>
                <span class="tname">{{ item.camp2.team_name }}</span>
                <span class="score" :class="{ win: item.camp2.is_win }">{{ item.camp2.score }}</span>
                <br />
              </template>
            </a-list-item-meta>
          </a-list-item>
        </template>
        <template #header>
          <div class="title">王者荣耀职业联赛2023年夏季赛赛程</div>
        </template>
      </a-list>
    </div>

    <div class="matchInfo">
      <div class="seasontitle"><b>2023年KPL夏季赛</b></div>
      <div class="scoreInfo">
        <img :src=camp1.team_icon class="img">
        <span class="tname1">{{ camp1.team_name }}</span>
        <span class="scorevalue" :class="{ win: camp1.score > camp2.score }">{{ camp1.score }}</span>
        <span class="maohao">:</span>
        <span class="scorevalue" :class="{ win: camp1.score < camp2.score }">{{ camp2.score }}</span>
        <span class="tname2">{{ camp2.team_name }}</span>
        <img :src=camp2.team_icon class="img">
      </div>
      <div class="selectBattle">
        <span class="selectBattleButton" :class="{ sbattle: index == sb }" @click="selectABattle(index)"
          v-for="(item, index) in match" :key="item.battle_seq">
          {{ toChinese(index + 1) }}
        </span>
      </div>
      <div class="battleInfo">
        <div class="score">
          <div class="blue">
            <img :src=bcamp1.team_icon class="img">
            <span class="tname1">{{ bcamp1.team_name }}</span>
          </div>
          <span class="scorevalue">{{ bcamp1.kill_num }}</span>
          <span class="maohao">:</span>
          <span class="scorevalue">{{ bcamp2.kill_num }}</span>
          <div class="red">
            <span class="tname2">{{ bcamp2.team_name }}</span>
            <img :src=bcamp2.team_icon class="img">
          </div>
        </div>
        <div class="bplist">
          <img :src=bp14.hero_icon class="ban">
          <img :src=bp12.hero_icon class="ban">
          <div class="empty"></div>
          <img :src=bp3.hero_icon class="ban">
          <img :src=bp1.hero_icon class="ban">
          <div class="space"></div>
          <img :src=bp2.hero_icon class="ban">
          <img :src=bp4.hero_icon class="ban">
          <div class="empty"></div>
          <img :src=bp11.hero_icon class="ban">
          <img :src=bp13.hero_icon class="ban">
        </div>
        <div class="bplist">
          <img :src=bp17.hero_icon class="pick">
          <img :src=bp16.hero_icon class="pick">
          <img :src=bp9.hero_icon class="pick">
          <img :src=bp8.hero_icon class="pick">
          <img :src=bp5.hero_icon class="pick">
          <div class="space"></div>
          <img :src=bp6.hero_icon class="pick">
          <img :src=bp7.hero_icon class="pick">
          <img :src=bp10.hero_icon class="pick">
          <img :src=bp15.hero_icon class="pick">
          <img :src=bp18.hero_icon class="pick">
        </div>
        <div class="rec">
          <img src="https://game.gtimg.cn/images/yxzj/matchdata/location-icon1.png">
          <span>{{ bcamp1.push_tower_num }}</span>
          <img src="https://game.gtimg.cn/images/yxzj/matchdata/location-icon2.png">
          <span class="space1">{{
            bcamp1.kill_big_dragon_num + bcamp1.kill_dark_tyrant_num + bcamp1.kill_prophet_dragon_num +
            bcamp1.kill_shadow_dragon_num + bcamp1.kill_storm_dragon_king_num }}</span>
          <span class="space2">{{
            bcamp2.kill_big_dragon_num + bcamp2.kill_dark_tyrant_num + bcamp2.kill_prophet_dragon_num +
            bcamp2.kill_shadow_dragon_num + bcamp2.kill_storm_dragon_king_num }}</span>
          <img src="https://game.gtimg.cn/images/yxzj/matchdata/location-icon6.png">
          <span>{{ bcamp2.push_tower_num }}</span>
          <img src="https://game.gtimg.cn/images/yxzj/matchdata/location-icon5.png">

        </div>
        <div class="eco">
          <span class="goldvalue1">{{ bcamp1.gold }}</span>
          <span class="goldvalue2">{{ bcamp2.gold }}</span>
        </div>
        <div class="line">
          <div class="blue" :style="{ flex: bcamp1.gold }"></div>
          <div class="white"></div>
          <div class="red" :style="{ flex: bcamp2.gold }"></div>
        </div>
      </div>
      <div class="players">
        <div class="playerInfo">
          <div>
            <img class="heroicon1" :src=p1.hero_icon>
          </div>
          <div>
            <div>
              <span>{{ p1.gold }}</span>
              <span>{{ p1.kill_num + "/" + p1.death_num + "/" + p1.assist_num }}</span>
              <span>{{ p1.player_name }}</span>
            </div>
            <div>
              <img class="skill1" :src=p1ab.summoner_ability_icon>
            </div>
          </div>
          <div class="space"></div>
          <div>
            <div>
              <span>{{ p2.player_name }}</span>
              <span>{{ p2.kill_num + "/" + p2.death_num + "/" + p2.assist_num }}</span>
              <span>{{ p2.gold }}</span>
            </div>
            <div>
              <img class="skill2" :src=p2ab.summoner_ability_icon>
            </div>
          </div>
          <div>
            <img class="heroicon2" :src=p2.hero_icon>
          </div>
        </div>
        <div class="playerInfo">
          <div>
            <img class="heroicon1" :src=p3.hero_icon>
          </div>
          <div>
            <div>
              <span>{{ p3.gold }}</span>
              <span>{{ p3.kill_num + "/" + p3.death_num + "/" + p3.assist_num }}</span>
              <span>{{ p3.player_name }}</span>
            </div>
            <div>
              <img class="skill1" :src=p3ab.summoner_ability_icon>
            </div>
          </div>
          <div class="space"></div>
          <div>
            <div>
              <span>{{ p4.player_name }}</span>
              <span>{{ p4.kill_num + "/" + p4.death_num + "/" + p4.assist_num }}</span>
              <span>{{ p4.gold }}</span>
            </div>
            <div>
              <img class="skill2" :src=p4ab.summoner_ability_icon>
            </div>
          </div>
          <div>
            <img class="heroicon2" :src=p4.hero_icon>
          </div>
        </div>
        <div class="playerInfo">
          <div>
            <img class="heroicon1" :src=p5.hero_icon>
          </div>
          <div>
            <div>
              <span>{{ p5.gold }}</span>
              <span>{{ p5.kill_num + "/" + p5.death_num + "/" + p5.assist_num }}</span>
              <span>{{ p5.player_name }}</span>
            </div>
            <div>
              <img class="skill1" :src=p5ab.summoner_ability_icon>
            </div>
          </div>
          <div class="space"></div>
          <div>
            <div>
              <span>{{ p6.player_name }}</span>
              <span>{{ p6.kill_num + "/" + p6.death_num + "/" + p6.assist_num }}</span>
              <span>{{ p6.gold }}</span>
            </div>
            <div>
              <img class="skill2" :src=p6ab.summoner_ability_icon>
            </div>
          </div>
          <div>
            <img class="heroicon2" :src=p6.hero_icon>
          </div>
        </div>
        <div class="playerInfo">
          <div>
            <img class="heroicon1" :src=p7.hero_icon>
          </div>
          <div>
            <div>
              <span>{{ p7.gold }}</span>
              <span>{{ p7.kill_num + "/" + p7.death_num + "/" + p7.assist_num }}</span>
              <span>{{ p7.player_name }}</span>
            </div>
            <div>
              <img class="skill1" :src=p7ab.summoner_ability_icon>
            </div>
          </div>
          <div class="space"></div>
          <div>
            <div>
              <span>{{ p8.player_name }}</span>
              <span>{{ p8.kill_num + "/" + p8.death_num + "/" + p8.assist_num }}</span>
              <span>{{ p8.gold }}</span>
            </div>
            <div>
              <img class="skill2" :src=p8ab.summoner_ability_icon>
            </div>
          </div>
          <div>
            <img class="heroicon2" :src=p8.hero_icon>
          </div>
        </div>
        <div class="playerInfo">
          <div>
            <img class="heroicon1" :src=p9.hero_icon>
          </div>
          <div>
            <div>
              <span>{{ p9.gold }}</span>
              <span>{{ p9.kill_num + "/" + p9.death_num + "/" + p9.assist_num }}</span>
              <span>{{ p9.player_name }}</span>
            </div>
            <div>
              <img class="skill1" :src=p9ab.summoner_ability_icon>
            </div>
          </div>
          <div class="space"></div>
          <div>
            <div>
              <span>{{ p10.player_name }}</span>
              <span>{{ p10.kill_num + "/" + p10.death_num + "/" + p10.assist_num }}</span>
              <span>{{ p10.gold }}</span>
            </div>
            <div>
              <img class="skill2" :src=p10ab.summoner_ability_icon>
            </div>
          </div>
          <div>
            <img class="heroicon2" :src=p10.hero_icon>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import config from '@/config';

export default {

  data() {
    return {
      results: [],
      sm: 0,
      sc: '',//选中的match
      camp1: '',
      camp2: '',
      match: [],
      sb: '',//选中的battle
      matchitem: '',
      battle: '',
      bcamp1: '',
      bcamp2: '',
      bplist: '',
      bp1: '',
      bp2: '',
      bp3: '',
      bp4: '',
      bp5: '',
      bp6: '',
      bp7: '',
      bp8: '',
      bp9: '',
      bp10: '',
      bp11: '',
      bp12: '',
      bp13: '',
      bp14: '',
      bp15: '',
      bp16: '',
      bp17: '',
      bp18: '',
      plist: [],
      p1: '',
      p2: '',
      p3: '',
      p4: '',
      p5: '',
      p6: '',
      p7: '',
      p8: '',
      p9: '',
      p10: '',
      p1ab: '',
      p2ab: '',
      p3ab: '',
      p4ab: '',
      p5ab: '',
      p6ab: '',
      p7ab: '',
      p8ab: '',
      p9ab: '',
      p10ab: '',



    };
  },
  mounted() {

    this.$http.get(config.burl + "/schedule").then(
      (res) => {
        this.results = res.data.data;
        this.selectMatch(0);
      }
    );

  },
  methods: {
    notSameDay(i) {
      return !(this.formatDate(this.results[i].start_time) == this.formatDate(this.results[i - 1].start_time));
    },
    formatDate(dateString) {
      const inputDate = new Date(dateString);
      const year = inputDate.getFullYear();
      const month = inputDate.getMonth() + 1;
      const day = inputDate.getDate();
      const formattedDateTime = `${year}年 ${month}月${day}日`;
      return formattedDateTime;
    },
    selectMatch(i) {
      this.sm = i;
      this.sc = this.results[this.sm];
      this.camp1 = this.sc.camp1;
      this.camp2 = this.sc.camp2;
      this.$http.get(config.burl + "/match?match_id=" + this.sc.match_id).then(
        (res) => {
          this.match = res.data.data[0].results;
          this.selectABattle(0);
        }
      );
    },
    selectABattle(i) {
      this.sb = i;
      this.matchitem = this.match[this.sb];
      this.$http.get(config.burl + "/battle?battle_id=" + this.matchitem.battle_id).then(
        (res) => {
          this.battle = res.data.data[0].data;
          this.bcamp1 = this.battle.camp1;
          this.bcamp2 = this.battle.camp2;
          this.bplist = this.battle.bp_list
          this.bp1 = this.bplist[0];
          this.bp2 = this.bplist[1];
          this.bp3 = this.bplist[2];
          this.bp4 = this.bplist[3];
          this.bp5 = this.bplist[4];
          this.bp6 = this.bplist[5];
          this.bp7 = this.bplist[6];
          this.bp8 = this.bplist[7];
          this.bp9 = this.bplist[8];
          this.bp10 = this.bplist[9];
          this.bp11 = this.bplist[10];
          this.bp12 = this.bplist[11];
          this.bp13 = this.bplist[12];
          this.bp14 = this.bplist[13];
          this.bp15 = this.bplist[14];
          this.bp16 = this.bplist[15];
          this.bp17 = this.bplist[16];
          this.bp18 = this.bplist[17];


          this.plist = this.battle.battle_player_list;
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "对抗路" && this.plist[i].camp == 1) {
              this.p1 = this.plist[i];
              this.p1ab = this.p1.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "对抗路" && this.plist[i].camp == 2) {
              this.p2 = this.plist[i];
              this.p2ab = this.p2.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "打野" && this.plist[i].camp == 1) {
              this.p3 = this.plist[i];
              this.p3ab = this.p3.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "打野" && this.plist[i].camp == 2) {
              this.p4 = this.plist[i];
              this.p4ab = this.p4.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "中路" && this.plist[i].camp == 1) {
              this.p5 = this.plist[i];
              this.p5ab = this.p5.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "中路" && this.plist[i].camp == 2) {
              this.p6 = this.plist[i];
              this.p6ab = this.p6.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "发育路" && this.plist[i].camp == 1) {
              this.p7 = this.plist[i];
              this.p7ab = this.p7.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "发育路" && this.plist[i].camp == 2) {
              this.p8 = this.plist[i];
              this.p8ab = this.p8.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "游走" && this.plist[i].camp == 1) {
              this.p9 = this.plist[i];
              this.p9ab = this.p9.SummonerAbilityInfo;
            }
          }
          for (i = 0; i < 10; i++) {
            if (this.plist[i].position_desc == "游走" && this.plist[i].camp == 2) {
              this.p10 = this.plist[i];
              this.p10ab = this.p10.SummonerAbilityInfo;
            }
          }
        }
      );




    },
    millisToMAndS(milliseconds) {
      // 计算分钟和秒数
      var minutes = Math.floor(milliseconds / (1000 * 60));
      var seconds = Math.floor((milliseconds % (1000 * 60)) / 1000);

      // 格式化为“mm:ss”
      var formattedMinutes = (minutes < 10) ? '0' + minutes : minutes;
      var formattedSeconds = (seconds < 10) ? '0' + seconds : seconds;

      return formattedMinutes + ':' + formattedSeconds;
    },
    toChinese(i) {
      switch (i) {
        case 1: return "第一局";
        case 2: return "第二局";
        case 3: return "第三局";
        case 4: return "第四局";
        case 5: return "第五局";
        case 6: return "第六局";
        case 7: return "第七局";
        default: return "wrong index";
      }


    }

  }


};
</script>

<style scoped>
.layout{
display: flex;
}
.schedulelist {
  margin: 30px 40px 0 120px;
  width: 17%;
  min-width: 300px;
  box-shadow: 0 0 10px 5px rgba(0, 0, 0, 0.2);
  height: calc(82vh);
  overflow-y: scroll;
  /* float: left; */
}

.schedulelist img {
  width: 36px;
  /* float: left; */
  margin: 0 20px 0 10px;
  border-radius: 20%;
}



.schedulelist .tname {
  font-size: larger;
  float: left;
  font: bold;
  margin-top: 5px;
}

.schedulelist .score {
  font-size: 22px;
  float: right;
  font: bold;
  margin-right: 10px;
}

.true {
  color: gold;
}

.schedulelist .date {
  padding-left: 14px;
  font-size: large;
  font: bold;
  float: left;
}

.schedulelist .ant-list-split .ant-list-item {
  border-block-end: 2px solid rgba(5, 5, 5, 0.1);
}

.schedulelist .win {
  color: rgb(255, 223, 40);
}

.schedulelist .title {
  font: bolder;
}

.schedulelist .ant-list-item {
  border-left: solid 8px transparent;
  transition: 0.8s;
}

.schedulelist .ant-list-item:hover {
  border-left: solid 8px rgb(255, 196, 0);
}

.schedulelist .smatch {
  border-left: solid 8px rgb(255, 196, 0);
}

.matchInfo {
  margin: 30px 40px 0 40px;
  width: 50%;
  min-width: 600px;
  box-shadow: 0 0 10px 5px rgba(0, 0, 0, 0.2);
  height: calc(82vh);
  overflow-y: scroll;
  float: left;
}


.seasontitle {
  background-color: #efefef;
  padding: 13px;
  font: bold;
  font-size: 20px;
  box-shadow: 0 0px 10px 0 rgba(0, 0, 0, 0.2);
}

.scoreInfo {
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.scoreInfo .scorevalue {
  padding: 0 5px 0 5px;
  border-radius: 18%;
  background-color: #a9a9a9;
  font-size: 35px;
  font: bold;
  color: #ffffff;
}


.scoreInfo .win {
  color: gold;
}

.scoreInfo .maohao {
  font-size: 35px;
  margin: 0 15px 0 15px;
}

.scoreInfo .tname1 {
  margin-right: 90px;
  font-size: 18px;
}

.scoreInfo .tname2 {
  margin-left: 90px;
  font-size: 18px;
}


.scoreInfo .img {
  width: 60px;
  margin: 0 20px 0 10px;
  border-radius: 20%;
  float: left;
}


.selectBattle {
  display: flex;
  justify-content: space-between;
  box-shadow: 0 4px 10px 0 rgba(0, 0, 0, 0.2);
  margin-bottom: 20px;
}

.selectBattle .selectBattleButton {
  height: 38px;
  width: 100%;
  background: #ececec;
  transition: 0.5s;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: large;
  font: bolder;
}

.selectBattle .selectBattleButton:hover {
  background: white;
  color: rgb(255, 183, 0);
}

.selectBattle .sbattle {
  background: white;
  color: rgb(255, 183, 0);
}

.battleInfo {
  height: 320px;
  align-items: center;
  justify-content: center;
}

.battleInfo .score {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.battleInfo .score .blue {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to right, rgb(0, 187, 255, 0.6), white);
}

.battleInfo .score .red {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to left, rgba(255, 47, 0, 0.6), white);
}

.battleInfo .score .scorevalue {
  padding: 0 5px 0 5px;
  font-size: 24px;
  font: bold;
}

.battleInfo .score .maohao {
  font-size: 24px;
  margin: 0 8px 0 8px;
}

.battleInfo .score .tname1 {
  margin-right: 120px;
  font-size: 18px;
}

.battleInfo .score .tname2 {
  margin-left: 120px;
  font-size: 18px;
}


.battleInfo .score .img {
  width: 45px;
  margin: 0 20px 0 10px;
  border-radius: 20%;
  float: left;
}

.battleInfo .bplist {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
}

.battleInfo .bplist .space {
  width: 200px;
}

.battleInfo .bplist .empty {
  width: 40px;
}

.battleInfo .bplist .ban {
  filter: grayscale(100%);
  border-radius: 15%;
  width: 40px;
}

.battleInfo .bplist .pick {
  border-radius: 15%;
  width: 40px;
}


.battleInfo .rec {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
}

.battleInfo .rec img {
  width: 24px;
}

.battleInfo .rec span {
  font-size: 20px;
  font: bolder;
  margin: 0 10px;
}

.battleInfo .rec .space1 {
  margin-right: 200px;
}

.battleInfo .rec .space2 {
  margin-left: 200px;
}


.battleInfo .eco {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
}

.battleInfo .eco .goldvalue1 {
  margin-right: 250px;
}

.battleInfo .eco .goldvalue2 {
  margin-left: 250px;
}

.battleInfo .line {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 20px;
  width: 74%;
  margin: 0 auto 0 auto;
}

.battleInfo .line .blue {
  background-color: rgb(0, 187, 255, 0.6);
  padding: 6px;
  box-sizing: border-box;
}

.battleInfo .line .white {
  background-color: rgba(255, 255, 255, 0);
  width: 3px;
}

.battleInfo .line .red {
  background-color: rgba(255, 47, 0, 0.6);
  padding: 6px;
  box-sizing: border-box;
}


.playerInfo {
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.playerInfo .heroicon1 {
  width: 60px;
  border-radius: 15%;
  border: blue 1px solid;
  margin: 10px 0;
}

.playerInfo .heroicon2 {
  width: 60px;
  border-radius: 15%;
  border: red 1px solid;
}

.playerInfo .skill1 {
  width: 30px;
  border-radius: 50%;
  display: block;
  margin: 0 12px;
  float: left;
}

.playerInfo .skill2 {
  width: 30px;
  border-radius: 50%;
  display: block;
  margin: 0 12px;
  float: right;
}

.playerInfo span {
  margin: 0 10px;
}

.playerInfo .space {
  width: 140px;
}

.players {
  margin: 0 auto;
}
</style>