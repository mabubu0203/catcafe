import {RiotComponentExport} from 'riot'
import {XApiKeyGenerateResponse} from '../../axios-front/model/xapi-key-generate-response';

export interface XApiKeyGenerateResponseComponentState {
  x_api_key?: XApiKeyGenerateResponse;
}

export interface XApiKeyGenerateResponseComponentProps {
}

export interface XApiKeyGenerateResponseComponent extends RiotComponentExport<XApiKeyGenerateResponseComponentProps, XApiKeyGenerateResponseComponentState> {
  state: XApiKeyGenerateResponseComponentState;

  xApiKeyGenerate(): void;
}