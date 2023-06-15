$(document).ready(function() {

    function printMessages() {
        $.ajax({
            type: "GET",
            url: "/api/messages",
            success: function(messages) {
                for (var i = 0; i < messages.length; i++) {
                    console.log(messages[i]);
                }
            },
            error: function() {
                toastr.error("Ocorreu um erro ao obter a lista de mensagens.");
            }
        });
    }

    $("#formulario").submit(function(event) {
        event.preventDefault();

        var email = $("#email").val();
        var assunto = $("#assunto").val();
        var mensagem = $("#mensagem").val();

        if (email.trim() === "" && assunto.trim() === "" && mensagem.trim() === "" ||
            email.trim() === "" || assunto.trim() === "" || mensagem.trim() === "") {
            toastr.error("Os campos estão vazios. Por favor, preencha-os antes de enviar.");
        } else {
            $.ajax({
                type: "POST",
                url: "/api/messages",
                contentType: "application/json",
                data: JSON.stringify({ email: email, assunto: assunto, mensagem: mensagem }),
                success: function() {
                    toastr.success("Mensagem enviada com sucesso! Tenha um maravilhoso Dia dos Namorados!");
                    printMessages();
                },
                error: function() {
                    toastr.error("Ocorreu um erro ao enviar a mensagem. Por favor, tente novamente.");
                }
            });
        }

        $("#email").val("");
        $("#assunto").val("");
        $("#mensagem").val("");
    });

    printMessages();
});
