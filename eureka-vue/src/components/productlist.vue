<template>
<el-main>
  <el-form :inline="true" :model="queryFame" class="demo-form-inline">
    <el-form-item label="商品名称">
      <el-input v-model="queryFame.name"></el-input>
    </el-form-item>
    <el-form-item label="是否上架">
      <el-select v-model="queryFame.upper_Status" placeholder="活动区域">
        <el-option label="上架" value=1></el-option>
        <el-option label="下架" value=0></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%">
  <!--  @selection-change="handleSelectionChange">-->
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>

    <el-table-column
      prop="id"
      label="编号"
      width="120">
    </el-table-column>
    <el-table-column
      prop="name"
      label="商品名称"
      width="120">
    </el-table-column>
    <el-table-column
      prop="price"
      label="价格"
      width="120">
    </el-table-column>
    <el-table-column
      label="商品图片"
      width="120">
     <template slot-scope="scope">
       <img :src="scope.row.imge_url" style="height: 36px">
     </template>
    </el-table-column>
    <el-table-column
      prop="dase"
      label="上架状态"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      label="日期"
      width="120">
      <template slot-scope="scope">{{ scope.row.factory_date }}</template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.row.id)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.row.id)">删除</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="getid(scope.row.id)">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    background
    layout="prev, pager, next"
    @current-change="gotopage"
    :page-count="pages">
  </el-pagination>

  <el-form>
  <el-form-item>
    <button type="primary" @click="add">添加</button>
  </el-form-item>
  </el-form>

  <el-dialog
  :visible.sync="adds"
  ><el-form ref="form" :model="saveFame" label-width="80px">
    <el-form-item label="商品名称">
      <el-input v-model="saveFame.name"></el-input>
    </el-form-item>
    <el-form-item label="商品价格">
      <el-input v-model="saveFame.price"></el-input>
    </el-form-item>
    <el-form-item label="商品图片">
      <el-upload
        class="avatar-uploader"
        action="http://localhost/file/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="saveFame.imge_url" :src="saveFame.imge_url" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="是否上架">
      <el-input v-model="saveFame.dase"></el-input>
    </el-form-item>
    <el-form-item label="出厂时间">
      <el-date-picker type="date" placeholder="选择日期" v-model="saveFame.factory_date" style="width: 100%;"></el-date-picker>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmits">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>

  </el-dialog>

  <el-dialog
    :visible.sync="upts"
  ><el-form ref="form" :model="uptFame" label-width="80px">
    <el-form-item label="商品名称">
      <el-input v-model="uptFame.name"></el-input>
    </el-form-item>
    <el-form-item label="商品价格">
      <el-input v-model="uptFame.price"></el-input>
    </el-form-item>
    <el-form-item label="是否上架">
      <el-input v-model="uptFame.dase"></el-input>
    </el-form-item>
    <el-form-item label="出厂时间">
        <el-date-picker type="date" placeholder="选择日期" v-model="uptFame.factory_date" style="width: 100%;"></el-date-picker>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="uptsa">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>

  </el-dialog>
  <el-dialog
    :visible.sync="getids"
  ><el-form ref="form" :model="getFame" label-width="80px">
    <el-form-item label="商品名称">
      <el-input v-model="getFame.name"></el-input>
    </el-form-item>
    <el-form-item label="商品价格">
      <el-input v-model="getFame.price"></el-input>
    </el-form-item>
    <el-form-item label="是否上架">
      <el-input v-model="getFame.dase">
        </el-input>
    </el-form-item>
    <el-form-item label="出厂时间">
      <el-date-picker type="date" placeholder="选择日期" v-model="getFame.factory_date" style="width: 100%;"></el-date-picker>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="shutdown">关闭</el-button>
    </el-form-item>
  </el-form>

  </el-dialog>
</el-main>
</template>

<script>

  let axios=require("axios")
  let bacepack="http://localhost/"

    export default {
        name: "productlist",
        data(){
            return{
                tableData:[],
                pages:0,
                queryFame:{
                    pageNum:1,
                    pageSize:3,
                },
                adds:false,
                upts:false,
                getids:false,
                saveFame:{},
                uptFame:{},
                getFame:{},
                fileName:null
            }
        },
        methods:{
            handleAvatarSuccess(res, file) {
                console.log(res,file)
                //给文件url赋值
                this.saveFame.imge_url = res.fileUrl;
                console.log(this.saveFame.imge_url)
                //删除图片
                if(this.fileName!=null){+
                    axios({
                        url:bacepack+"file/delByFileName",
                        method:"get",
                        params:{fileName:this.fileName}
                    }).then(res=>{
                        console.log("文件删除："+res.data.result)
                    })
                }
                //保存原文件名
                this.fileName = res.fileName;


            },
            beforeAvatarUpload(file) {
              /*  const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;*/
              return true;

        },
            shutdown(){
              this.getids=false
            },
            getid(id){
                this.getids=true
                let self=this
              axios({
                  url:bacepack+"shoop/getid",
                  methods:"get",
                  params:{id,id}
              }).then(function (res) {
                  self.getFame=res.data
              })
            },

            uptsa(){
                let self=this
                axios({
                    url:bacepack+"shoop/upt",
                    methods:"post",
                    data:self.uptFame
                }).then(function (res) {
                    self.getList()
                    self.upts=false
                })

            },
            add(){
                this.saveFame={imge_url:""}
                this.adds=true

            },
            onSubmits(){
                let self=this
                axios({
                url:bacepack+"shoop/save",
                method:"post",
                data:self.saveFame
                }).then(function (res) {
                    self.getList()
                    self.adds=false
                })
            },

            handleDelete(id){
                let self=this
                axios({
                    url:bacepack+"shoop/del",
                    methods:"get",
                    params: {id:id},
                }).then(function (res) {
                    self.getList();
                })
            },
            handleEdit(id){
                let self=this
                this.upts=true
                axios({
                    url:bacepack+"shoop/getid",
                    methods:"get",
                    params:{id,id},
                }).then(function (res) {
                    self.uptFame=res.data
                })

            },
            onSubmit(){
                this.queryFame.pageNum=1
                this.getList();
            },
            getList(){
                let self=this
                axios({
                    url:bacepack+"shoop/list",
                    methods: "get",
                    params:self.queryFame
                }).then(function (res) {
                    console.log(res)
                    self.tableData=res.data.list
                    self.pages=res.data.pages
                })
            },
            gotopage(pageNum){
            this.queryFame.pageNum=pageNum
                this.getList()
            }
        },
        created() {
            this.getList();
        }
    }
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
