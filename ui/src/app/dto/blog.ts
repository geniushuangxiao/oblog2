import { BlogAttachment } from './blog-attachment';
export class Blog {
    id: number;
    categoryId: string;
    name: string;
    content: string;
    desc: string;
    release: boolean;
    author: string;
    firstWriteTime: number;
    lastModifyTime: number;
    viewNum: number;
    blogAttachment: Array<BlogAttachment>;
}
