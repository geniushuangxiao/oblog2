export class BlogAttachment {
    uid: number;
    name: string;
    // 状态有：uploading done error removed
    status: string;
    // 服务端响应内容
    response: string;
    // 下载文件链接
    url: string;
}
