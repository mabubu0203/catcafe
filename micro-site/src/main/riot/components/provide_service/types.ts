import {RiotComponentExport} from 'riot'
import {ProvideServiceDetail} from '../../axios-front/model/provide-service-detail';

export interface ProvideServiceDetailComponentState {
}

export interface ProvideServiceDetailComponentProps {
}

export interface ProvideServiceDetailComponent extends RiotComponentExport<ProvideServiceDetailComponentProps, ProvideServiceDetailComponentState> {
  state: ProvideServiceDetailComponentState;
}

export interface ProvideServiceListComponentState {
  provide_services?: Array<ProvideServiceDetail>;
}

export interface ProvideServiceListComponentProps {
}

export interface ProvideServiceListComponent extends RiotComponentExport<ProvideServiceListComponentProps, ProvideServiceListComponentState> {
  state: ProvideServiceListComponentState;

  provideServiceSearch(e): void;
}