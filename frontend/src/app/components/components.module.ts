import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ComponentsRoutingModule } from './components-routing.module';

import { SidebarComponent } from './sidebar/sidebar.component';
@NgModule({
  declarations: [SidebarComponent],
  imports: [
    CommonModule
  ],
  exports: [
    SidebarComponent
  ]
})
export class ComponentsModule { }
