console.log("boardComment In.");
console.log(bnoVal);
console.log(document.getElementById('email').value);

document.getElementById('cmtPostBtn').addEventListener('click', () => {

    const cmtText = document.getElementById('cmtText');
    if (cmtText.value == null || cmtText.value == '') {
        alert('댓글의 내용이 없습니다.');
        cmtText.focus;
        return false;
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtText.value
        };
        postCommentToServer(cmtData).then(result => {
            if (result == '1') {
                alert('댓글 등록 성공');
                cmtText.value = "";
            }
            spreadCommentList(bnoVal);
        });
    }

})

async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        console.log(cmtData);
        const resp = await fetch(url, config);
        const result = await resp.text();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(bno, page) {
    try {
        console.log("/comment/" + bno + "/" + page);
        const resp = await fetch("/comment/" + bno + "/" + page);
        const result = await resp.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
}
function spreadCommentList(bno, page = 1) {
    getCommentListFromServer(bno, page).then(result => {
        console.log(result.cmtList);

        if (result.cmtList.length > 0) {
            const ul = document.getElementById('cmtListArea');
            if (page == 1) {
                ul.innerHTML = '';
            }
            for (let cvo of result.cmtList) {
                let li = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">`;
                li += `${cvo.writer}</div>`;
                li += `${cvo.content}</div>`;
                li += `</div> <span class="badge rounded-pill text-bg-warning">${cvo.modAt}</span>`;
                li += `<button type="button" class="btn btn-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                li += `<button type="button" class="btn btn-danger btn-sm del" id="cmtDelBtn">삭제</button>`;
                li += `</li>`;
                ul.innerHTML += li;
            }
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            if (result.pgvo.pageNo < result.endPage) {
                moreBtn.style.visibility = 'visible';
                moreBtn.dataset.page = page + 1;
            } else {
                moreBtn.style.visibility = 'hidden';
            }
        } else {
            const ul = document.getElementById('cmtListArea').innerHTML = `
            <li class="list-group-item">
            <div class="mb-3">
            <div class="fw-bold">Writer</div>
            Content
			</div> <span class="badge rounded-pill text-bg-warning">modAt</span>
            </li>`;
            let li = `<li>There is No Comment.</li>`;
            ul.innerHTML += li;
        }

    });

}

document.addEventListener('click', (e) => {
    console.log(e.target);
    if (e.target.id == 'moreBtn') {
        let page = parseInt(e.target.dataset.page)
        spreadCommentList(bnoVal, page);
    } else if (e.target.classList.contains('mod')) {
        let li = e.target.closest('li');
        let cmtText = li.querySelector('.fw-bold').nextSibling;

        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;

        document.getElementById('cmtModBtn').setAttribute("data-cno", li.dataset.cno);
        document.getElementById('cmtModBtn').setAttribute("data-writer", li.dataset.writer);
    } else if (e.target.id == 'cmtModBtn') {
        let cmtDataMod = {
            cno: e.target.dataset.cno,
            writer: e.target.dataset.writer,
            content: document.getElementById('cmtTextMod').value
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if (result == "1") {
                alert("수정성공!!");
                document.querySelector('.btn-close').click();
            } else {
                alert("수정실패!!");
                document.querySelector('.btn-close').click();
            }
            spreadCommentList(bnoVal);
        })
    } else if (e.target.classList.contains('del')) {
        let li = e.target.closest('li');

        document.getElementById('cmtDelBtn').setAttribute("data-cno", li.dataset.cno);
        document.getElementById('cmtDelBtn').setAttribute("data-writer", li.dataset.writer);
        let cmtDataDel = {
            cno: e.target.dataset.cno,
            writer: e.target.dataset.writer,
        };
        console.log(cmtDataDel);
        delCommentToServer(cmtDataDel).then(result => {
            if (result == "1") {
                alert("삭제성공!!");
            } else {
                alert("삭제실패!!");
            }
            spreadCommentList(bnoVal);
        })
    }
})

async function editCommentToServer(cmtDataMod) {
    try {
        const url = '/comment/edit';
        const config = {
            method: 'put',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function delCommentToServer(cmtDataMod) {
    try {
        const url = '/comment/delete/' + cmtDataMod.cno + '/' + cmtDataMod.writer;
        const config = {
            method: 'delete',
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}