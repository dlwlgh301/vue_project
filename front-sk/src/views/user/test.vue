<template>
    <div style="margin: 100px;">
        <form action="/account/fileUpload" id="fileUpload" name="fileUpload" method="post" enctype="multipart/form-data">
            이름 :
            <input type="text" name="name" id="cmd" value="namevla" />
            <br />파일 :
            <input type="file" name="file" />
            <br />
            <input type="submit" name="업로드" value="제출" />
            <br />
        </form>
    </div>
</template>
