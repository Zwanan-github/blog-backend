package link.zwanan.zwananblog.model.vo.response;

public abstract class Response<Model> {
    public abstract Object copyEntity(Model model);
}
