import {RiotComponentExport} from 'riot'
import {NoticeDetail} from '../../axios-front/model/notice-detail';

export interface NoticeDetailComponentState {
}

export interface NoticeDetailComponentProps {
}

export interface CastDetailComponent extends RiotComponentExport<NoticeDetailComponentProps, NoticeDetailComponentState> {
    state: NoticeDetailComponentState;
}

export interface NoticeListComponentState {
    notices?: Array<NoticeDetail>;
}

export interface NoticeListComponentProps {
}

export interface NoticeListComponent extends RiotComponentExport<NoticeListComponentProps, NoticeListComponentState> {
    state: NoticeListComponentState;

    noticeSearch(): void;
}