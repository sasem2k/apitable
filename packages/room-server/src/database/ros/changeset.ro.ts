import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty } from 'class-validator';
import { ApiTipIdEnum } from 'shared/enums/string.enum';

export class ChangesetRo {
  @ApiProperty({
    type: String,
    required: true,
    example: 'dst0Yj5aNeoHldqvf6',
    description: 'datasheet ID',
  })
  @IsNotEmpty({ context: { tipId: ApiTipIdEnum.apiParamsEmptyError }})
    dstId: string;

  @ApiProperty({
    type: String,
    required: true,
    example: '0Yj5aNeoHldqvf6',
    description: 'message ID',
  })
  @IsNotEmpty({ context: { tipId: ApiTipIdEnum.apiParamsEmptyError }})
    messageId: string;
}
