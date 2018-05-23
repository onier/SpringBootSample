$(function () {
    function getOrgs() {
        var orgJson = $.getJSON({url: "http://localhost:2018/user/listOrgs", async: false});
        var orgs = orgJson.responseJSON;
        var select = document.getElementById("createORGListSelect");
        for (var n = 0; n < orgs.length; n++) {
            select.options[select.options.length] = new Option(orgs[n].name, orgs[n].id);
        }
        select = document.getElementById("editorORGListSelect");
        for (var n = 0; n < orgs.length; n++) {
            select.options[select.options.length] = new Option(orgs[n].name, orgs[n].id);
        }
        return orgs;
    }

    function initOrg() {
        $('#orgTable').DataTable({
            "language": {
                "url": "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Chinese.json"
            },
            data: getOrgs(),
            "columns": [
                {"data": "id"},
                {"data": "name"},
                {"data": "description"},
                {"data": "depth"},
                {"data": "parentName"},
                {"data": "org_path"}
            ]
        });
    }

    $('#createORG').on("click", function () {
        $.ajax({
            type: 'POST',
            url: "http://localhost:2018/user/saveOrg",
            dataType: "json",
            data: {name: $('#createORGname').val(), description: $('#createORGtext').val(), parent_id: $('#createORGListSelect').val()},
            success: function (data) {
                location.reload();
            }
        });
    });

    initOrg();

    var table = $('#orgTable').DataTable();

    $('#orgTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#deleteOrg').click(function () {
        var oData = table.rows('.selected').data();

        for (var i = 0; i < oData.length; i++) {
            var id = oData[0].id;
            $.ajax({
                type: 'POST',
                url: "http://localhost:2018/user/deleteOrg/" + id,
                dataType: "json",
                success: function (data) {
//                    initOrg();
                    location.reload();
                }
            });
        }
        table.row('.selected').remove().draw(false);

    });

    var select;
    $('#editorORGButton').click(function () {
        var oData = table.rows('.selected').data();
        select = oData[0];
        $("#editorORGname").val(oData[0].name);
        $("#editorORGtext").val(oData[0].description);
        $("#editorORGListSelect").val(oData[0].parent_id);
    });

    $("#editorORGSubmit").on("click", function () {
        $.ajax({
            type: 'POST',
            url: "http://localhost:2018/user/editor",
            dataType: "json",
            data: {id: select.id, name: $('#editorORGname').val(), description: $('#editorORGtext').val(), parent_id: $('#editorORGListSelect').val()},
            success: function (data) {
                location.reload();
            }
        });
    });
})
 