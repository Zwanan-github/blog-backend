package top.zwanan.www.model.vo.response;

public abstract class Response<Model> {
    public abstract Object copyEntity(Model model);
}
